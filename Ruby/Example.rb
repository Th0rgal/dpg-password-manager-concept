require 'sha3'

if  __FILE__ == $0

  # DPG Method
  def dpg (salts, password_size, allowed_chars)
    # Seed generation (using sha3 256)
    s = SHA3::Digest.new(:sha256)
    s << salts.join
    seed = s.hexdigest.to_i 16

    puts seed

    # Password generation (using a deterministic random number generator)
    output = ""
    password_size.times do
      seed = (1_103_515_245 * seed + 12345) % 999_999_937
      output += allowed_chars[seed%allowed_chars.size]
    end
    return output
  end


  # Example use
  puts dpg(["website.com", "thomas", "", "ex@mple"], 40,
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")


  # Example implementation
  salts = []
  fields = ["website url", "login", "additional salt", "main password"]
  for field in fields
      print "Enter " + field + ": "
      salts << gets.chomp
  end
  print "password size: "
  password_size = gets.chomp.to_i
  print "allowed chars: "
  allowed_chars = gets.chomp
  puts "output: " + dpg(salts, password_size, allowed_chars)

end